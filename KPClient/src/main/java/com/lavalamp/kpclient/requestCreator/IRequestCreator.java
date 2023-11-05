package com.lavalamp.kpclient.requestCreator;

import pojo.IPogo;
import request.IRequest;

import java.io.Serializable;

public interface IRequestCreator {
    IRequest CreateRequest(Serializable data);
}
