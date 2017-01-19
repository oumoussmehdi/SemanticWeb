package linking;

import java.util.HashMap;
import java.util.List;
import data.*;

import com.wcohen.ss.AbstractStatisticalTokenDistance;
import com.wcohen.ss.ApproxNeedlemanWunsch;
import com.wcohen.ss.CharMatchScore;
import com.wcohen.ss.DirichletJS;
import com.wcohen.ss.Jaro;
import com.wcohen.ss.JaroWinkler;
import com.wcohen.ss.JensenShannonDistance;
import com.wcohen.ss.Level2JaroWinkler;
import com.wcohen.ss.Level2Levenstein;
import com.wcohen.ss.MongeElkan;
import com.wcohen.ss.MongeElkanTFIDF;
import com.wcohen.ss.MultiStringAvgDistance;
import com.wcohen.ss.NeedlemanWunsch;
import com.wcohen.ss.SmithWaterman;
import com.wcohen.ss.SoftTokenFelligiSunter;
import com.wcohen.ss.TagLink;
import com.wcohen.ss.TokenFelligiSunter;
import com.wcohen.ss.UnsmoothedJS;

/**
 *
 */
public class LinkData {
	//    static final MongeElkan  m = new MongeElkan();
	static final SmithWaterman  m = new SmithWaterman();

	public static HashMap findSimiliraties(List data1, List data2, String dataType){
		HashMap mappings = new HashMap();
		if (dataType.contains("person1")) {
			for (Person person1 : (List<Person>) data1) {
				double maxScore = -99;
				Person correspondingPerson2 = null;
				// find the person in the second dataset that is similar to the current person in the first dataset

				for(Person person2 : (List<Person>) data2){
					// score of matching
					double Score = simPersDist(person1, person2);
					if(Score > maxScore){
						maxScore = Score;
						correspondingPerson2 = person2;
					}
				}
				// add the 2 similar persons to hashMap List
				mappings.put(person1.getURI(), correspondingPerson2.getURI());
			}
		}else if (dataType.contains("person2")) {
			for (Person person2 : (List<Person>) data2) {
				double maxScore = -99;
				Person correspondingPerson1 = null;
				// find the person in the second dataset that is similar to the current person in the first dataset

				for(Person person1 : (List<Person>) data1){
					// score of matching
					double Score = simPersDist2(person1, person2);
					if(Score > maxScore){
						maxScore = Score;
						correspondingPerson1 = person1;
					}
				}
				// add the 2 similar persons to hashMap List
				mappings.put(person2.getURI(), correspondingPerson1.getURI());
			}
		}else{
			for (Restaurant restaurant1 : (List<Restaurant>) data1) {
				float maxScore = -99;
				Restaurant correspondingRestaurant2 = new Restaurant();
				// find the Restaurant in the second dataset that is similar to the current Restaurant in the first dataset
				for(Restaurant restaurant2 : (List<Restaurant>) data2){
					// score of matching
					float Score = simRestDist(restaurant1, restaurant2);
					if(Score > maxScore){
						maxScore = Score;
						correspondingRestaurant2 = restaurant2;
					}
				}
				// add the 2 similar Restaurants to hashMap List
				mappings.put(restaurant1.getURI(), correspondingRestaurant2.getURI());
			}
		}
		return mappings;
	}

	public static double simPersDist(Person person1, Person person2) {
		double score = 0;
		// name
		double givenNameScore = m.score(person1.getGiven_name(),person2.getGiven_name());
		if (!Double.isNaN(givenNameScore)){
			score += givenNameScore;
		}
		//surname
		double surnameScore = m.score(person1.getSurname(),person2.getSurname());
		if (!Double.isNaN(surnameScore)){
			score += surnameScore;
		}
		// Age
		int age1 = 0;
		try{
			age1 = Integer.parseInt(person1.getAge());
		}catch (Exception e) {}
		int age2 = 0;
		try{
			age2 = Integer.parseInt(person2.getAge());
		}catch (Exception e) {}
		double diff = (Math.abs(age1-age2)*1.0)/(Math.max(age1, age2));
		double age_score = 1-(diff);
		//double age_score = m.score(person1.getAge(),person2.getAge());
		if (!Double.isNaN(age_score)){
			score += age_score;
		}


		//birth date
		double birth_score = m.score(person1.getDate_of_birth(),person2.getDate_of_birth());
		if (!Double.isNaN(birth_score)){
			score += birth_score;
		}

		//social_security_number
		/*double ssn_score = m.score(person1.getSocial_security_number(),person2.getSocial_security_number());
        if (!Double.isNaN(ssn_score)){
            score += ssn_score;
        }*/

		//phone number
		/*double phnum_score = m.score(person1.getPhone_number(),person2.getPhone_number());
        if (!Double.isNaN(phnum_score)){
            score += phnum_score;
        }*/

		//Street
		double street_score = m.score(person1.getStreet(),person2.getStreet());
		if (!Double.isNaN(street_score)){
			score += street_score;
		}
		//house number
		/*double housenum_score = m.score(person1.getHouse_number(),person2.getHouse_number());
        if (!Double.isNaN(housenum_score)){
            score += housenum_score;
        }*/
		//PostCode
		double postcode_score = m.score(person1.getPostcode(),person2.getPostcode());
		if (!Double.isNaN(postcode_score)){
			score += postcode_score;
		}

		//suburb
		double suburb_score = m.score(person1.getSuburb_name(),person2.getSuburb_name());
		if (!Double.isNaN(suburb_score)){
			score += suburb_score;
		}

		//state
		double state_score = m.score(person1.getState(),person2.getState());
		if (!Double.isNaN(state_score)){
			score += state_score;
		}
		return score;
	}
	
	public static double simPersDist2(Person person1, Person person2) {
		double score = 0;
		// name
		double givenNameScore = m.score(person1.getGiven_name(),person2.getGiven_name());
		if (!Double.isNaN(givenNameScore)){
			score += givenNameScore;
		}
		//surname
		double surnameScore = m.score(person1.getSurname(),person2.getSurname());
		if (!Double.isNaN(surnameScore)){
			score += surnameScore;
		}
		// Age
		int age1 = 0;
		try{
			age1 = Integer.parseInt(person1.getAge());
		}catch (Exception e) {}
		int age2 = 0;
		try{
			age2 = Integer.parseInt(person2.getAge());
		}catch (Exception e) {}
		double diff = (Math.abs(age1-age2)*1.0)/(Math.max(age1, age2));
		double age_score = 1-(diff);
		//double age_score = m.score(person1.getAge(),person2.getAge());
		if (!Double.isNaN(age_score)){
			score += age_score;
		}


		//birth date
		double birth_score = m.score(person1.getDate_of_birth(),person2.getDate_of_birth());
		if (!Double.isNaN(birth_score)){
			score += birth_score;
		}

		//social_security_number
		/*double ssn_score = m.score(person1.getSocial_security_number(),person2.getSocial_security_number());
        if (!Double.isNaN(ssn_score)){
            score += ssn_score;
        }*/

		//phone number
		/*double phnum_score = m.score(person1.getPhone_number(),person2.getPhone_number());
        if (!Double.isNaN(phnum_score)){
            score += phnum_score;
        }*/

		//Street
		double street_score = m.score(person1.getStreet(),person2.getStreet());
		if (!Double.isNaN(street_score)){
			score += street_score;
		}
		//house number
		/*double housenum_score = m.score(person1.getHouse_number(),person2.getHouse_number());
        if (!Double.isNaN(housenum_score)){
            score += housenum_score;
        }*/
		//PostCode
		double postcode_score = m.score(person1.getPostcode(),person2.getPostcode());
		if (!Double.isNaN(postcode_score)){
			score += postcode_score;
		}

		//suburb
		double suburb_score = m.score(person1.getSuburb_name(),person2.getSuburb_name());
		if (!Double.isNaN(suburb_score)){
			score += suburb_score;
		}

		//state
		double state_score = m.score(person1.getState(),person2.getState());
		if (!Double.isNaN(state_score)){
			score += state_score;
		}
		return score;
	}
	private static float simRestDist(Restaurant restaurant1, Restaurant restaurant2) {
		float score = 0;
		score += m.score(restaurant1.getName(),restaurant2.getName());
		score += m.score(restaurant1.getCategory(),restaurant2.getCategory());
		score += m.score(restaurant1.getCity_name(),restaurant2.getCity_name());
		score += m.score(restaurant1.getPhone_number(),restaurant1.getPhone_number());
		score += m.score(restaurant1.getStreet(),restaurant1.getStreet());
		return score;
	}

}
