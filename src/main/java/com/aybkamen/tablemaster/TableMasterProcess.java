package com.aybkamen.tablemaster;

/**
 *
 * @author aybkamen
 */
public class TableMasterProcess {
    
    private final String originalCommand;
    private String textResult;

    TableMasterProcess(String command) {
        originalCommand = command;        
    }

    String getTextResult() {
        return textResult;
    }

    void process(TableManager tableManager) {
        if (isTableCommand()) {
            final String tableCommand = getTableCommand();
            textResult = tableManager.getTableResult("table");
        } else {
            textResult = originalCommand;
        }
    }

    private boolean isTableCommand() {
        return originalCommand.startsWith("[[") && originalCommand.endsWith("]]");
    }

    private String getTableCommand() {
        return originalCommand.substring(2, originalCommand.length()-2);
    }
    
}
