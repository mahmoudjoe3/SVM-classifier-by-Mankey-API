package com.Mahmoudjoe3;
import com.monkeylearn.MonkeyLearn;
import com.monkeylearn.MonkeyLearnException;
import com.monkeylearn.MonkeyLearnResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Main {

    public static void main(String[] args) throws MonkeyLearnException{
	// write your code here

        MonkeyLearn ml = new MonkeyLearn("6db9e2d00a5c7f6392454ce60d8f88cf372349ab");
        String modelId = "cl_Hva35P8B";
        String[] data = {"this is not good"};
        MonkeyLearnResponse res = ml.classifiers.classify(modelId, data, true);

        prediction prediction=new prediction(res.arrayResult);

        System.out.println( "confidence :: "+prediction.confidence+"\n"+"Label :: "+prediction.label);
    }
    static class prediction{
        double confidence;
        String label;

        public prediction(JSONArray root) {
            JSONArray root2= (JSONArray) root.get(0);
            JSONArray root3= (JSONArray) root2.get(0);
            JSONObject jsonObject= (JSONObject) root3.get(0);
            Double confidence= (Double) jsonObject.get("confidence");
            confidence*=100;
            String label= (String) jsonObject.get("label");

            this.confidence=confidence;
            this.label=label;
        }
    }
}
