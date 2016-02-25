package com.aybkamen.tablemaster;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import static org.mockito.Mockito.*;

/**
 *
 * @author aybkamen
 */
public class TableMasterProcessTest {
    
    private TableManager tableManager;
    
    @Before
    public void setUp() {
        tableManager = mock(TableManager.class);
    }
    
    
    @Test
    public void test_text_entry_returns_same_text() {
        final String constantText = "This is a constant text entry";
        
        TableMasterProcess tm = new TableMasterProcess(constantText);
        
        tm.process(tableManager);
        
        String result = tm.getTextResult();
        
        assertEquals(constantText, result);
    }

    @Test
    public void test_table_master_can_process_basic_table_entries() {
        final String command = "[[table]]";
        final String tableResult = "table result";
        
        //before
        when(tableManager.getTableResult("table")).thenReturn(tableResult);
        
        TableMasterProcess tm = new TableMasterProcess(command);
        
        tm.process(tableManager);
        
        //when
        String result = tm.getTextResult();
        
        //then
        assertEquals(tableResult, result);
    }

    @Test
    public void test_tableMaster_can_process_mixed_commands() {
        final String command ="The [[color]] [[monster]] attacks";
        final String color = "red";
        final String monster = "dragon";
        
        // before
        when(tableManager.getTableResult("color")).thenReturn(color);
        when(tableManager.getTableResult("monster")).thenReturn(monster);
        
        TableMasterProcess tm = new TableMasterProcess(command);
        tm.process(tableManager);
        
        //when
        String result = tm.getTextResult();
        
        //then
        assertEquals("The red dragon attacks", result);
    }
}
