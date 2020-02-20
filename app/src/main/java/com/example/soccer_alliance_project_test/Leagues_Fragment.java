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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.ArrayList;


public class Leagues_Fragment extends Fragment {

    public NavController DashboardNavController;

    private RecyclerView league_recycler_view;
    private Context context;
    private ArrayList<Comman_Data_List> comman_data_List;
    private Comman_adapter comman_adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leagues, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DashboardNavController = Navigation.findNavController(getActivity(),R.id.dashboard_host_fragment);
        context = getActivity().getApplicationContext();

        /*--------league Adapter Configuration--------*/
        league_recycler_view = view.findViewById(R.id.leagues_recycler_view);
        comman_data_List = new ArrayList<Comman_Data_List>();
        comman_adapter = new Comman_adapter(comman_data_List,context);
        league_recycler_view.setLayoutManager(new LinearLayoutManager(context));
        league_recycler_view.setAdapter(comman_adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        getLeagueList();
       /* comman_data_List.clear();
        comman_data_List.add(new Comman_Data_List("League 1",R.drawable.user));
        comman_data_List.add(new Comman_Data_List("League 2",R.drawable.user));
        comman_data_List.add(new Comman_Data_List("League 3",R.drawable.user));
        comman_data_List.add(new Comman_Data_List("League 4",R.drawable.user));
        comman_data_List.add(new Comman_Data_List("League 5",R.drawable.user));
        comman_adapter.notifyDataSetChanged();
        comman_adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DashboardNavController.navigate(R.id.teamListFragment);
            }
        });
*/
    }
       private void getLeagueList(){

           comman_data_List.clear();

           final StringRequest stringRequest = new StringRequest(Request.Method.GET,"https://soccerallianceapp.appspot.com/rest/api/listOfLeague_guestDashboard", new Response.Listener<String>() {
               @Override
               public void onResponse(String response) {
                   try {
                       JSONObject jsonObject = new JSONObject(response);
                       if(jsonObject.getInt("Status")==200){
                           JSONArray jsonArray = jsonObject.getJSONArray("Leagues");
                           for(int i=0;i<jsonArray.length();i++){
                               JSONObject leagues_object = jsonArray.getJSONObject(i);
                               comman_data_List.add(new Comman_Data_List(
                                       leagues_object.getString("name"),
                                       leagues_object.getString("logo"),
                                       leagues_object.getInt("league_id")));

                           }
                           comman_adapter.notifyDataSetChanged();

                           comman_adapter.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View view) {

                                   RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
                                   int position = viewHolder.getAdapterPosition();


                                   /* Bundle data = new Bundle();
                                   data.putString("league_id",String.valueOf(comman_data_List.get(position).getItem_id()));
                                   data.putString("coming_from","Leagues_Class");
                                   DashboardNavController.navigate(R.id.teamListFragment,data);
                                    */
                               }
                           });
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

}
