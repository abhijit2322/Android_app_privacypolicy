package abhijit.osdm2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import abhijit.osdm2.constants.GlobalVariables;
import abhijit.osdm2.models.Admin;
import abhijit.osdm2.models.FlatOwner;
import abhijit.osdm2.rettrofitsupport.RetrofitClient;
import abhijit.osdm2.service.HerokuService;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile_Owner_Activity extends AppCompatActivity {


    //@BindView(R.id.profile_bg_main)
    ImageView mProfileImage;
  //  @BindView(R.id.profile_img)
    ImageView mProfileSImage;
    String status="";
   // @BindView(R.id.app_bar)
    //Toolbar toolbar;
    String onumber="";
    private static String flatStatus;
     TextView owner_name,owner_phone,owner_email,owner_flatnumner;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.profile_activity_main);
            ButterKnife.bind(this);

            Intent intent = getIntent();
            String oname = intent.getStringExtra("ownername");
            onumber = intent.getStringExtra("flatnumber");
            String ophone = intent.getStringExtra("ownerphonno");
            String oemail = intent.getStringExtra("owneremailid");

            FlatOwner flatOwner=new FlatOwner();
            flatOwner.setFlatnumber(onumber);
            flatStatus=GetFlatStatus(flatOwner);
            AppSettingsData.setFlatstatus(flatStatus);

            System.out.println(" flat status :>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> flat number: "+onumber+" status is rentend:  "+flatStatus+" Login Flatowner: "+AppSettingsData.getLoginFlatOwner());
            System.out.println("From application conext -----------------AppSettingsData.getFlatstatus() "+ AppSettingsData.getFlatstatus());
            owner_name=(TextView)findViewById(R.id.owner_name);
            owner_flatnumner=(TextView)findViewById(R.id.owner_flatnumber);
            owner_phone=(TextView)findViewById(R.id.owner_ch_no);//owner_ph_no_work);//owner_ph_no);//owner_ph_no_work);//
            owner_email=(TextView)findViewById(R.id.parent_email);


            owner_name.setText(oname);
            owner_flatnumner.setText(onumber);
            owner_phone.setText(ophone);
            owner_email.setText(oemail);


            mProfileImage =(ImageView) findViewById(R.id.profile_bg_main);
            mProfileImage.setImageResource(R.drawable.omd2image);
            mProfileSImage=(ImageView) findViewById(R.id.profile_img_main);
            Picasso.with(this).load(R.drawable.omd2image).centerCrop().fit().into(mProfileImage);
            Picasso.with(this).load(R.drawable.omd2image).centerCrop().fit().into(mProfileSImage);
            mProfileSImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("GlobalVariables -rule  (Profile_Owner_Activity)>>>>>>>>>>>>>>>>>> is rented  "+flatStatus);

                    if(AppSettingsData.GetRule().contains("admin")) {
                        System.out.println("GlobalVariables -rule  (Profile_Owner_Activity)>>>>>>>>>>>>>>>>>>     admin"+"  is rented  "+flatStatus);
                       /*if(flatStatus.contains("no")) */{
                           Intent opa = new Intent(v.getContext(), Edit_Owner_Profile.class);
                           opa.putExtra("ownername", owner_name.getText());
                           opa.putExtra("flatnumber", owner_flatnumner.getText());
                           opa.putExtra("ownerphonno", owner_phone.getText());
                           opa.putExtra("owneremailid", owner_email.getText());
                           v.getContext().startActivity(opa);
                       }
                       /*else if(flatStatus.contains("yes")){
                           Intent opa = new Intent(v.getContext(), Tenent_Update_Activity.class);
                           opa.putExtra("ownername", owner_name.getText());
                           opa.putExtra("flatnumber", owner_flatnumner.getText());
                           opa.putExtra("ownerphonno", owner_phone.getText());
                           opa.putExtra("owneremailid", owner_email.getText());
                           v.getContext().startActivity(opa);
                       }*/
                      /* else
                       {
                           System.out.println("GlobalVariables -rule>>>>>>>>>>>>>>>>>>(Profile_Renter_Activity)    from admin Nowhere ");
                           Intent ir = new Intent(getApplicationContext(), NoWhereActivity.class);
                           startActivity(ir);
                       }*/

                    }
                    else if(AppSettingsData.GetRule().contains("owner") & onumber.contains(AppSettingsData.getLoginFlatOwner())) {


                       if(flatStatus.contains("yes") & AppSettingsData.getLoginFlatOwner().contains(onumber)) {
                            System.out.println("GlobalVariables -rule>>>>>>>>>>>>>>>>>>(Profile_Owner_Activity)     owner ");
                            Intent opa = new Intent(v.getContext(), Tenent_Update_Activity.class);
                            opa.putExtra("ownername", owner_name.getText());
                            opa.putExtra("flatnumber", owner_flatnumner.getText());
                            opa.putExtra("ownerphonno", owner_phone.getText());
                            opa.putExtra("owneremailid", owner_email.getText());
                            v.getContext().startActivity(opa);
                         }
                       else{
                            if(AppSettingsData.getLoginFlatOwner().contains(onumber)) {
                                Intent opa = new Intent(v.getContext(), Edit_Owner_Profile.class);
                                opa.putExtra("ownername", owner_name.getText());
                                opa.putExtra("flatnumber", owner_flatnumner.getText());
                                opa.putExtra("ownerphonno", owner_phone.getText());
                                opa.putExtra("owneremailid", owner_email.getText());
                                v.getContext().startActivity(opa);
                            }
                            else
                            {
                               /* System.out.println("GlobalVariables -rule>>>>>>>>>>>>>>>>>>(Profile_Renter_Activity)   fromowner   Nowhere ");
                                Intent ir = new Intent(getApplicationContext(), NoWhereActivity.class);
                                startActivity(ir);*/
                                AppSettingsData.AppDialog("Update Fail","Please login with your ",AppSettingsData.getLoginFlatOwner(),Profile_Owner_Activity.this);
                            }
                         }
                    }
                    else
                    {

                     //   System.out.println("GlobalVariables -rule>>>>>>>>>>>>>>>>>>(Profile_Renter_Activity)  Global   Nowhere ");
                     //   Intent ir = new Intent(getApplicationContext(), NoWhereActivity.class);
                    //    startActivity(ir);
                       /* new AlertDialog.Builder(Profile_Owner_Activity.this)
                                .setTitle("You are not the owner of this flat")
                                .setMessage("You are not the Owner of the flat kindly update your flat which is "+AppSettingsData.getLoginFlatOwner())
                                .setCancelable(false)
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Whatever...
                                        finish();
                                    }
                                }).show();*/
                        AppSettingsData.AppDialog("Update Fail","Please login with your ",AppSettingsData.getLoginFlatOwner(),Profile_Owner_Activity.this);
                    }
                    //startActivity(new Intent(getApplicationContext(), Edit_Owner_Profile.class));
                }
            });
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            if(item.getItemId() == android.R.id.home) {
                finish();
            }
            return super.onOptionsItemSelected(item);
        }

    public String GetFlatStatus(FlatOwner flatOwner){
        String url = "https://postgres2322.herokuapp.com/flatowner/";
        HerokuService apiService = RetrofitClient.getApiService();
        apiService.getFlatStatus(flatOwner).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println("the Response  >>>>>. in login activity "+response.body().toString());
                System.out.println("Updated Password the Response  >>>>> SUCCESS>>>>>>");
                // admin_s= response.body();
                 status=response.body().toString();
                //  response_status=true;
            }

            @Override
            public void onFailure(Call<String>  call, Throwable t) {
                System.out.println("This in  Failure >>>>>. in login activity "+t.getMessage());
                // response_status=true;

            }
        });
        return status;
    }

}
