package me.ronygomes.pattern.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rony Gomes <rgomes.bd@gmail.com>
 * @since 2015.12.13
 */
public class HtmlTable {

    private List<String> headers;
    private List<List<String>> dataGrid;

    public HtmlTable() {
        headers = new ArrayList<>();
        dataGrid = new ArrayList<>();
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public List<List<String>> getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(List<List<String>> dataGrid) {
        this.dataGrid = dataGrid;
    }

    public String toHtml() {

        int headerLength = headers.size();
        if (headerLength == 0) {
            return null;
        }

        String htmlTable = "<table><th>";
        for (String header : headers) {
            htmlTable += "<td>" + header + "</td>";
        }
        htmlTable += "</th>";

        for (List<String> row : dataGrid) {
            if (row.isEmpty()) {
                continue;
            }
            htmlTable += "<tr>";

            for (String cell : row) {
                htmlTable += "<td>" + cell + "</td>";
            }
            htmlTable += "</tr>";
        }
        htmlTable += "</table>";
        return htmlTable;
    }

    public static class Builder {

        private List<String> headers;
        private List<List<String>> dataGrid;
        private List<String> currentRow;

        public Builder() {
            headers = new ArrayList<>();
            dataGrid = new ArrayList<>();
        }

        public Builder addHeader(String header) {
            headers.add(header);
            return this;
        }

        public Builder addRow() {
            currentRow = new ArrayList<>();
            dataGrid.add(currentRow);
            return this;
        }

        public Builder addCell(String cell) {
            currentRow.add(cell);
            return this;
        }

        public HtmlTable build() {
            HtmlTable table = new HtmlTable();
            table.setHeaders(this.headers);
            table.setDataGrid(this.dataGrid);
            return table;
        }
    }
}
