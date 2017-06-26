package com.rentmanager.entity;

import java.util.List;
import java.util.Map;

/**
 * @author : tanmay
 * @created : 26-Jun-2017
 */
public class Table {
	private List<Map<String, Object>> tableData;
	private String[] tableDataOrder;
	private Boolean actionsEnabled;

	public List<Map<String, Object>> getTableData() {
		return tableData;
	}

	public void setTableData(List<Map<String, Object>> tableData) {
		this.tableData = tableData;
	}

	public String[] getTableDataOrder() {
		return tableDataOrder;
	}

	public void setTableDataOrder(String[] tableDataOrder) {
		this.tableDataOrder = tableDataOrder;
	}

	public Boolean getActionsEnabled() {
		return actionsEnabled;
	}

	public void setActionsEnabled(Boolean actionsEnabled) {
		this.actionsEnabled = actionsEnabled;
	}
}
