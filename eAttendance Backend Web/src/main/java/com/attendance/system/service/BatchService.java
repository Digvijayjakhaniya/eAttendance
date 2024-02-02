package com.attendance.system.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.attendance.system.model.Batch;

public interface BatchService {

	public ResponseEntity<List<Batch>> getAllBatches();
	public ResponseEntity<String> addBatch(Batch batch);
	public ResponseEntity<String> updateBatch(Integer bid, String batchName);
	public ResponseEntity<Integer> deleteBatch(Integer bid);
	public ResponseEntity<Batch> getBatch(Integer bid);
}
