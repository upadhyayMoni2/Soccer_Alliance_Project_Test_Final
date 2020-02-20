package com.example.soccer_alliance_project_test;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.card.MaterialCardView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Matches_Fragment extends Fragment implements View.OnClickListener {

    MaterialCardView um_cardView1,pm_cardView1;
    public NavController DashboardNavController;
    private Context context;
    TextView umc1_team1,umc1_team2,umc1_time,umc1_date;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_matches, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DashboardNavController = Navigation.findNavController(getActivity(),R.id.dashboard_host_fragment);
        context = getActivity().getApplicationContext();

        um_cardView1 = view.findViewById(R.id.um_cardView1);
        um_cardView1.setOnClickListener(this);

        pm_cardView1 = view.findViewById(R.id.pm_cardView1);
        pm_cardView1.setOnClickListener(this);

        umc1_team1 = view.findViewById(R.id.umc1_team1);
        umc1_date = view.findViewById(R.id.umc1_date);
        umc1_team2 = view.findViewById(R.id.umc1_team2);
    }

    @Override
    public void onClick(View view) {
        if(view == um_cardView1){
            DashboardNavController.navigate(R.id.upcomingMatchFragment);
        }
        else if(view == pm_cardView1){
            DashboardNavController.navigate(R.id.match_Score_Fragment);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getUpcomingMatchList();
        getPlayedMatchList();
    }


    private void getUpcomingMatchList(){

       // comman_data_List.clear();


        final StringRequest stringRequest = new StringRequest(Request.Method.GET,"https://soccerallianceapp.appspot.com/rest/api/upcomingMatches_guestDashboard", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getInt("Status")==200){
                        JSONArray jsonArray = jsonObject.getJSONArray("UpcomingMatchList");
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject leagues_object = jsonArray.getJSONObject(i);
                            umc1_team1.setText(leagues_object.getString("name"));
                            umc1_date.setText(leagues_object.getString("date_of_match"));
                            umc1_team2.setText(leagues_object.getString("name"));
                              /*comman_data_List.add(new Comman_Data_List(
                                    leagues_object.getString("name"),
                                    leagues_object.getString("logo"),
                                    leagues_object.getInt("league_id")));
*/
                        }
                       // comman_adapter.notifyDataSetChanged();

                       /* comman_adapter.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
                                int position = viewHolder.getAdapterPosition();


                                   *//* Bundle data = new Bundle();
                                   data.putString("league_id",String.valueOf(comman_data_List.get(position).getItem_id()));
                                   data.putString("coming_from","Leagues_Class");
                                   DashboardNavController.navigate(R.id.teamListFragment,data);
                                    *//*
                            }
                        });*/
                    }
                    else {

                        Toast.makeText(context,"Could not fetch data from server",Toast.LENGTH_SHORT).show();
                        //explore_progress_txt.setText(getResources().getString(R.string.some_wrong));
                    }

                } catch (JSONException e) {
                    Toast.makeText(context,e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

                }
            }
        },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context,error.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                    }
                }){
               /*@Override
               protected Map<String, String> getParams() throws AuthFailureError {

                   Map<String, String> params = new HashMap<String, String>();
                   params.put("user_id",PreferenceData.getUserID(context));
                   return params;
               }*/
        };
        RequestHandler.getInstance(context).addToRequestQueue(stringRequest);


    }

    private void getPlayedMatchList(){

     final StringRequest stringRequest = new StringRequest(Request.Method.GET,"https://soccerallianceapp.appspot.com/rest/api/playedMatches_guestDashboard", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getInt("Status")==200){
                        JSONArray jsonArray = jsonObject.getJSONArray("PlayedMatchList");
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject leagues_object = jsonArray.getJSONObject(i);

                        }

                    }
                    else {

                        Toast.makeText(context,"Could not fetch data from server",Toast.LENGTH_SHORT).show();
                        //explore_progress_txt.setText(getResources().getString(R.string.some_wrong));
                    }

                } catch (JSONException e) {
                    Toast.makeText(context,e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

                }
            }
        },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context,error.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                    }
                }){
                };
        RequestHandler.getInstance(context).addToRequestQueue(stringRequest);


    }

}
