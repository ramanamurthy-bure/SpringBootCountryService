package com.countryServices.demo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.countryServices.demo.beans.Country;
import com.countryServices.demo.controllers.AddResponse;

@Service
@Component
public class CountryService {

	static HashMap<Integer, Country> CountryIdMap;

	public CountryService() {
		CountryIdMap = new HashMap<Integer, Country>();

		Country indiaCountry = new Country(1, "India", "Delhi");
		Country usaCountry = new Country(2, "USA", "Washington");
		Country ukCountry = new Country(3, "UK", "London");

		CountryIdMap.put(1, indiaCountry);
		CountryIdMap.put(2, usaCountry);
		CountryIdMap.put(3, ukCountry);
	}

	public List<Country> getAllCountries() {
		List<Country> countries = new ArrayList<Country>(CountryIdMap.values());
		return countries;
	}

	public Country getCountryById(int cntryID) {
		Country country = CountryIdMap.get(cntryID);
		return country;
	}

	public Country getCountryByName(String ctryName) {
		Country country = null;
		for (int i : CountryIdMap.keySet()) {
			if (CountryIdMap.get(i).getCountryName().equals(ctryName))
				country = CountryIdMap.get(i);
		}
		return country;
	}

	public Country addCountry(Country country) {
		country.setCountryID(getMaxID());
		CountryIdMap.put(country.getCountryID(), country);
		return country;
	}

	// utility method to get id
	public static int getMaxID() {
		int max = 0;
		for (int ctryID : CountryIdMap.keySet())
			if (max <= ctryID)
				max = ctryID;
		return max + 1;
	}

	public Country updateCountry(Country country) {
		if (country.getCountryID() > 0)
			CountryIdMap.put(country.getCountryID(), country);
		return country;
	}

	public AddResponse deleteCountry(int ctryID) {
		CountryIdMap.remove(ctryID);
		AddResponse res = new AddResponse();
		res.setMessage("Country Deleted...");
		res.setId(ctryID);
		return res;
	}
}
