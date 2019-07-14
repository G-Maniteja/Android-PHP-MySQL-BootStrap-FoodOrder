package com.example.manitejagande.foodorder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class OrderHistoryDataModel {
    public String orderid1;
    public  String desc;
    public String total1;
    public  String status;

    public OrderHistoryDataModel(JSONObject obj) {
        try
        {
            this.orderid1 = obj.getString("orderid");
            this.desc = obj.getString("desc");
            this.total1=obj.getString("total");
            this.status=obj.getString("status");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public static ArrayList<OrderHistoryDataModel> fromJson(JSONArray jsonObjects)
    {
        ArrayList<OrderHistoryDataModel> orderitems = new ArrayList<OrderHistoryDataModel>();
        for (int i = 0; i < jsonObjects.length(); i++)
        {
            try
            {
                orderitems.add(new OrderHistoryDataModel(jsonObjects.getJSONObject(i)));
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        return orderitems;
    }

}
