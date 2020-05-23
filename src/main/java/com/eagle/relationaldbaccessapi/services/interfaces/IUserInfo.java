package com.eagle.relationaldbaccessapi.services.interfaces;

import com.eagle.relationaldbaccessapi.models.entity.UserInfoEntity;
import com.eagle.relationaldbaccessapi.services.interfaces.common.IFilesService;
import com.eagle.relationaldbaccessapi.services.interfaces.common.ISimpleCrud;

public interface IUserInfo extends IFilesService, ISimpleCrud<UserInfoEntity>{

}
