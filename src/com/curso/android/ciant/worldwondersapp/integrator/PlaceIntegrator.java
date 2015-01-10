package com.curso.android.ciant.worldwondersapp.integrator;

import com.curso.android.ciant.worldwondersapp.infrastructure.Constants;

public class PlaceIntegrator extends BaseIntegrator{

	public PlaceIntegrator() {
    }

    public String getAllPlace() {

        StringBuffer path = new StringBuffer();
        path.append(Constants.Integrator.WorldWondersApi.WORLD_WONDERS_LIST);

        String responseJson = this.doGetRequest(Constants.HTTP_PROTOCOL, Constants.Integrator.WorldWondersApi.HOST, path.toString());

        return responseJson;
    }
	
}
