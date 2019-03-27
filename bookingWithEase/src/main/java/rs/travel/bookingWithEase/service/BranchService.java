package rs.travel.bookingWithEase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.travel.bookingWithEase.model.Branch;
import rs.travel.bookingWithEase.repository.IBranchRepository;

@Service
public class BranchService {

	@Autowired
	private IBranchRepository branchRepository;
	
	public Optional<Branch> findOne(Long id) {
		return branchRepository.findById(id);
	}

	public List<Branch> findAll() {
		return branchRepository.findAll();
	}
	
	public Branch save(Branch branch) {
		return branchRepository.save(branch);
	}

	public void delete(Long id) {
		branchRepository.deleteById(id);
	}
	
}
