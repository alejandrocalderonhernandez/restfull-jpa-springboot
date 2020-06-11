package com.eagle.relationaldbaccessapi.services.interfaces;

import com.eagle.relationaldbaccessapi.models.dto.AddressDTO;
import com.eagle.relationaldbaccessapi.services.interfaces.common.IPageableService;
import com.eagle.relationaldbaccessapi.services.interfaces.common.ISimpleCrud;

public interface IAddressService extends ISimpleCrud<AddressDTO>, IPageableService<AddressDTO>{

	  static final String TYPE = "Address";
	  static final String DEFAULT_NAME_TO_SORT = "estate";
}
