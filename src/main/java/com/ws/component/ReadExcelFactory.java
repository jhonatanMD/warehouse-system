package com.ws.component;

import com.ws.entity.dto.upload.ProductDataDto;
import com.ws.entity.dto.upload.UploadExcelDataDto;
import com.ws.entity.dto.upload.ValidateExcelDataDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;


@Slf4j
@Component
public class ReadExcelFactory {


    private static final String FILE_ERROR = "archivo erroneo";
    private static final String FILE_EMPTY = "El archivo se encuentra vacio ";
    private static final String ROW_EMPTY = "Se encontro fila %s vacia ";
    private static final String HEADER_ERROR = "La cabecera fila ROW , columna CELL no es igual a la estructura" ;
    private static final String FILE_FORMAT_ERROR = "Validar que el archivo tenga el formato correcto" ;
    private static final String FILE_ERROR_DUPLICATE = "Error en el archivo: se encontro en la columna %s datos duplicados [%s]" ;
    private static final String VALUE_X = " , columna " ;

    private List<String> validationValue;

    private int countColumn = 0;
    @SneakyThrows
    public ValidateExcelDataDto get(MultipartFile file){

        validationValue = new ArrayList<>();
        ProductDataDto productData = new ProductDataDto();
        InputStream inp = file.getInputStream();
        List<String> errors = new ArrayList<>();

       try (Workbook wb = WorkbookFactory.create(inp)) {
           Sheet sh = wb.getSheetAt(0);
           Row row = sh.getRow(0);

           if(row == null) {
               errors.add(FILE_FORMAT_ERROR);
               return ValidateExcelDataDto.builder().errors(errors).build();
           }

           countColumn = row.getLastCellNum();


           if(countColumn == 0) {
               errors.add(FILE_ERROR);
               return ValidateExcelDataDto.builder().errors(errors).build();
           }
           if(sh.getPhysicalNumberOfRows() < 2)
               errors.add(FILE_EMPTY);


           Map<Integer,List<String>> valuesMap = new HashMap<>();
           Map<Integer,List<String>> errorMap = new HashMap<>();


           String[][] values = new String[sh.getPhysicalNumberOfRows()][countColumn];
           for (int rowIndex = 0; rowIndex < sh.getPhysicalNumberOfRows(); rowIndex++) {
               row = sh.getRow(rowIndex);
               cellProcess(row,values,valuesMap,errorMap,rowIndex, countColumn);
           }

           cleanErrors( errors, valuesMap,errorMap);

           productData.setData(new ArrayList<>());
          if(errors.isEmpty()){
              for (int i = 0; i < values.length ; i++) {
                    productData.getData().add(UploadExcelDataDto.builder()
                            .code(values[i][0])
                            .brand(values[i][1])
                            .name(values[i][2])
                            .category(values[i][3])
                            .type(values[i][4])
                            .material(values[i][5])
                            .status(values[i][6])
                            .stock(values[i][7])
                            .price(values[i][8])
                            .store(values[i][10]).build());
              }
          }

           return  ValidateExcelDataDto.builder()
                   .errors(errors)
                   .productData(productData)
                   .build();
       }
    }


    private void cellProcessData(String value , int rowIndex , int cellIndex , String[][] values , Map<Integer,List<String>> valuesMap , Map<Integer,List<String>> errorMap) {

        if (Objects.isNull(value) || value.trim().isEmpty()) {
            setMap(errorMap,rowIndex,cellIndex);
        }else {

            if(rowIndex != 0) {
                setMap(valuesMap,rowIndex,cellIndex);
                values[rowIndex - 1][cellIndex] = value;
            }

        }

    }
    private void cellProcess(Row row , String[][] values ,  Map<Integer,List<String>> valuesMap ,  Map<Integer,List<String>> errorMap ,  int...  data) {
        int rowIndex = data[0] ;
        int  numberColumn = data[1];
        for (int cellIndex = 0; cellIndex < numberColumn ; cellIndex++) {
            if(row != null) {
                Cell cell = row.getCell(cellIndex);
                if(Objects.nonNull(cell)) {
                    String value = setValue(cell);
                    cellProcessData(value,rowIndex,cellIndex,values,valuesMap , errorMap);
                    continue ;
                }

            }

            setMap(errorMap,rowIndex,cellIndex);
        }
    }
    private void cleanErrors(List<String> errors,  Map<Integer,List<String>> valuesMap ,  Map<Integer,List<String>> errorMap){
        if(valuesMap.size() > 0)
            for (int i = 0 ; i <= valuesMap.keySet().stream().mapToInt(v -> v).max().getAsInt() ; i ++){
                if(errorMap.containsKey(i))
                    errorMap.get(i).forEach(errors::add);
            }

    }

    private void setMap(Map<Integer,List<String>> map , int rowIndex , int cellIndex){
        List<String> s;
        if(map.containsKey(rowIndex - 1)) {
            s = map.get(rowIndex - 1);
        } else{
            s = new ArrayList<>();
        }
        s.add(String.format(ROW_EMPTY , (rowIndex + 1)) + VALUE_X + (cellIndex + 1));
        map.put(rowIndex - 1, s);

    }


    private String setValue(Cell cell) {

        String value = "";
        switch (cell.getCellType()) {
            case STRING:
                value = cell.getStringCellValue();
                break;
            case NUMERIC:
                DataFormatter formatter = new DataFormatter();
                log.info("NUMERIC " + formatter.formatCellValue(cell));
                value = formatter.formatCellValue(cell);
                break;
            case FORMULA:
                value = String.valueOf(cell.getCachedFormulaResultType());
                break;
            case BLANK:
            case ERROR:
            case _NONE:
                value = "";
                break;
            case BOOLEAN:
                value = String.valueOf(cell.getBooleanCellValue());
                break;

        }

        return value;
    }


    private void addValueValidation( int i ,String s){
        if(i == countColumn)
            validationValue.add(s);
    }

  /*  private void messageValidation(Sheet sh){
        AtomicReference<String> msg = new AtomicReference<>("");
        validationValue.stream()
                .map(Util::deleteCharacters)
                .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()))
                .forEach((k,v) -> {
                    if(v >= 2){
                        msg.updateAndGet(v1 -> v1 + k + ",");
                    }
                });

        if (!msg.get().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,  String.format(FILE_ERROR_DUPLICATE, sh.getRow(0).getCell(countColumn - 1),msg.get().substring(0,msg.get().length() - 1)));
        }

    }*/
}
