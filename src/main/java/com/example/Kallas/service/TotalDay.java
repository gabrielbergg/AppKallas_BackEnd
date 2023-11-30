package com.example.Kallas.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.Kallas.dto.DiaryDto;

@Service
public class TotalDay {

	public Double totDay(Page<DiaryDto> list) {
		Double tot = 0.0;

		LocalDateTime dt = LocalDateTime.now();

		for (DiaryDto d : list) {
			if (d.getTime_appointment().getDayOfMonth() == dt.getDayOfMonth()
					&& d.getTime_appointment().getMonth() == dt.getMonth()) {
				tot += d.getTotal_value();
			}
		}
		return tot;
	}
}
