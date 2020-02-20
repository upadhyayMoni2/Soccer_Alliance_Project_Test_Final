package com.example.soccer_alliance_project_test;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Player_List_Fragment extends Fragment implements View.OnClickListener {


    public NavController DashboardNavController;
    private RecyclerView player_recycler_view;
    private Context context;
    private ArrayList<Comman_Data_List> comman_data_List;
    private Comman_adapter comman_adapter;
    FloatingActionButton add_player_btn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DashboardNavController = Navigation.findNavController(getActivity(),R.id.dashboard_host_fragment);
        context = getActivity().getApplicationContext();


        add_player_btn = view.findViewById(R.id.add_player_btn);
        add_player_btn.setOnClickListener(this);
        if(getActivity().getIntent().getExtras()==null){
            add_player_btn.setVisibility(View.GONE);
        }
        /*--------Teams Adapter Configuration--------*/
        player_recycler_view = view.findViewById(R.id.player_recycler_view);
        comman_data_List = new ArrayList<Comman_Data_List>();
        comman_data_List.clear();

        /*comman_data_List.add(new Comman_Data_List("Player 1",R.drawable.user));
        comman_data_List.add(new Comman_Data_List("Player 2",R.drawable.user));
        comman_data_List.add(new Comman_Data_List("Player 3",R.drawable.user));
        comman_data_List.add(new Comman_Data_List("Player 4",R.drawable.user));
        comman_data_List.add(new Comman_Data_List("Player 5",R.drawable.user));*/

        comman_adapter = new Comman_adapter(comman_data_List,context);
        comman_adapter.notifyDataSetChanged();
        player_recycler_view.setLayoutManager(new LinearLayoutManager(context));
        player_recycler_view.setAdapter(comman_adapter);

    }

    @Override
    public void onClick(View view) {
        if(view == add_player_btn){
            DashboardNavController.navigate(R.id.add_Player_Fragment);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /*private void getPlayerList(){

        comman_data_List.clear();

        final StringRequest stringRequest = new StringRequest(Request.Method.GET,"https://soccerallianceapp.appspot.com/rest/api/viewPlayerListFromTeam&{team_id}", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getString("status").equals(200)){
                        JSONArray jsonArray = jsonObject.getJSONArray("Leagues");
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject leagues_object = jsonArray.getJSONObject(i);
                            comman_data_List.add(new Comman_Data_List(
                                    leagues_object.getString("league_id"),
                                    leagues_object.getString("name"),
                                    leagues_object.getString("logo")));
                        }
                        //exploreRecipeAdapter.notifyDataSetChanged();

                        comman_adapter.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
                                int position = viewHolder.getAdapterPosition();
                                if(!PreferenceData.getUserID(context).equals("")){
                                    Constants.add_to_History(context,PreferenceData.getUserID(context),comman_data_List.get(position).getRecipe_id());
                                }
                                Intent i = new Intent(context, Recipe_Details_Activity.class);
                                i.putExtra("recipe_id",comman_data_List.get(position).getRecipe_id());
                                startActivity(i);
                                getActivity().overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
                            }
                        });
                    }
                    else {

                        explore_sad_icon.setVisibility(View.VISIBLE);
                        //explore_progress_txt.setText(getResources().getString(R.string.some_wrong));
                    }

                } catch (JSONException e) {

                }
            }
        },
                new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
               *//*@Override
               protected Map<String, String> getParams() throws AuthFailureError {

                   Map<String, String> params = new HashMap<String, String>();
                   params.put("user_id",PreferenceData.getUserID(context));
                   return params;
               }*//*
        };
        RequestHandler.getInstance(context).addToRequestQueue(stringRequest);


    }*/


}
