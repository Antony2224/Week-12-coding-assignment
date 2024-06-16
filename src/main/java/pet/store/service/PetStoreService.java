package pet.store.service;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pet.store.controller.model.PetStoreData;
import pet.store.dao.PetStoreDao;
import pet.store.entity.PetStore;

@Service
public class PetStoreService {
	
	@Autowired
	private PetStoreDao petStoreDao; 
	
	@Transactional(readOnly = false)
	public PetStoreData savePetStore(PetStoreData petstoreData) {
		Long petStoreId = petstoreData.getPetStoreId();
		PetStore petStore = findOrCreatePetStore(petStoreId);
		
		copyPetStoreFields(petStore, petstoreData);
		return new PetStoreData(petStoreDao.save(petStore));
	}

	private void copyPetStoreFields(PetStore petStore, PetStoreData petstoreData) {
		// TODO Auto-generated method stub
		petStore.setPetStoreAddress(petstoreData.getPetStoreAddress());
		petStore.setPetStoreCity(petstoreData.getPetStoreCity());
		petStore.setPetStoreId(petstoreData.getPetStoreId());
		petStore.setPetStoreName(petstoreData.getPetStoreName());
		petStore.setPetStorePhone(petstoreData.getPetStorePhone());
		petStore.setPetStoreState(petstoreData.getPetStoreState());
		petStore.setPetStoreZip(petstoreData.getPetStoreZip());
	}

	private PetStore findOrCreatePetStore(Long petStoreId) {
		// TODO Auto-generated method stub
		if (Objects.isNull(petStoreId)) {
			return new PetStore();
		}
		else {
			return findPetStoreById(petStoreId);
		}
	}

	private PetStore findPetStoreById(Long petStoreId) {
		// TODO Auto-generated method stub
		return petStoreDao.findById(petStoreId)
				.orElseThrow(() -> new NoSuchElementException(
						"Pet store with ID=" + petStoreId + "was not found."));
	}
}
