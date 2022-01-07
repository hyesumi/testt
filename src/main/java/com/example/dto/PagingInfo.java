package com.example.dto;

import lombok.Data;

@Data
public class PagingInfo {

	public Integer perPage = 10;
	public Integer totalPage;
	public Integer currentPage;

}
