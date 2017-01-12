package com.coworkingnaminhasala;


import com.coworkingnaminhasala.controller.Validable;

import spark.Request;

public interface RequestHandler<V extends Validable> {

    Answer process(V value, Request request);

}
