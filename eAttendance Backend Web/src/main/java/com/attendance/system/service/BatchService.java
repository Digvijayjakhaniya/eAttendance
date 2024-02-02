package com.attendance.system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.attendance.system.dao.BatchDao;
import com.attendance.system.model.Batch;

@Service
public class BatchService {
	@Autowired
	BatchDao batchDao;

	public ResponseEntity<List<Batch>> getAllBatches() {
		try {
			return new ResponseEntity<List<Batch>>(batchDao.findAll(), HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<List<Batch>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<String> addBatch(Batch batch) {
		try {
			batchDao.save(batch);
			return new ResponseEntity<String>("<p class='text-success'>Batch added SuccessFully</p>", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("<p class='text-danger'>" + e.getMessage() + "</p>",
					HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<String> updateBatch(Integer bid, String batchName) {
		try {
			Batch batch = new Batch(bid, batchName);
			Batch dbBatch = batchDao.findById(bid).get();

			if (Objects.nonNull(batch.getBatchName()) && !"".equalsIgnoreCase(batch.getBatchName())) {
				dbBatch.setBatchName(batch.getBatchName());
			}
			batchDao.save(dbBatch);
			return new ResponseEntity<String>("<p class='text-success'>Batch Updated SuccessFully</p>", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("<p class='text-danger'>" + e.getMessage() + "</p>",
					HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<Integer> deleteBatch(Integer bid) {
		try {
			batchDao.deleteById(bid);
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Integer>(0, HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<Batch> getBatch(Integer bid) {
		try {
			return new ResponseEntity<Batch>(batchDao.findById(bid).get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

}
