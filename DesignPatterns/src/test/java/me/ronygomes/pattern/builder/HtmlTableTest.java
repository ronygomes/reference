package me.ronygomes.pattern.builder;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Rony Gomes <rgomes.bd@gmail.com>
 * @since 2015.12.13
 */
public class HtmlTableTest {

    private static final String OUTPUT = "<table><th><td>One</td><td>Two</td></th><tr><td>1</td><td>2</td></tr></table>";

    @Test
    public void createTable() {
        HtmlTable table = new HtmlTable();

        List<String> headers = new ArrayList<>();
        headers.add("One");
        headers.add("Two");
        table.setHeaders(headers);

        List<String> row = new ArrayList<>();
        row.add("1");
        row.add("2");

        List<List<String>> dataGrid = new ArrayList<>();
        dataGrid.add(row);

        table.setDataGrid(dataGrid);

        assertEquals(OUTPUT, table.toHtml());
    }

    @Test
    public void createTableUsingBuilder() {
        HtmlTable table = new HtmlTable.Builder()
                .addHeader("One")
                .addHeader("Two")
                .addRow()
                .addCell("1")
                .addCell("2")
                .build();

        assertEquals(OUTPUT, table.toHtml());
    }
}
