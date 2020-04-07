package abhijit.osdm2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import abhijit.osdm2.constants.GlobalVariables;
import abhijit.osdm2.models.Renter;
import abhijit.osdm2.rettrofitsupport.RetrofitClient;
import abhijit.osdm2.service.HerokuService;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile_Renter_Activity extends AppCompatActivity {

    ImageView mProfileImage;
    //  @BindView(R.id.profile_img)
    ImageView mProfileSImage;
    // @BindView(R.id.app_bar)
    //Toolbar toolbar;
    TextView rent_name, rent_phone, rent_email, rent_flatnumner;

    String[]monthName={"Jan.","Feb.","Mar.", "Apr.", "May.", "Jun.", "Jul.",
            "Aug.", "Sept.", "Oct.", "Nov.",
            "Dec."};

    Calendar c = Calendar.getInstance();
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_renter);





        Intent intent = getIntent();
        String rname = intent.getStringExtra("rentname");
        String rnumber = intent.getStringExtra("rentflatnumber");
        String rphone = intent.getStringExtra("rentphonno");
        String remail = intent.getStringExtra("rentemailid");
        String mntStatus = intent.getStringExtra("mntstatus");


        rent_name = (TextView) findViewById(R.id.renter_name);
        rent_flatnumner = (TextView) findViewById(R.id.renter_flatnumber);
        rent_phone = (TextView) findViewById(R.id.renter_ch_no);//owner_ph_no_work);//owner_ph_no);//owner_ph_no_work);//
        rent_email = (TextView) findViewById(R.id.renter_email_id);

        rent_name.setText(rname);
        rent_flatnumner.setText(rnumber);
        rent_phone.setText(rphone);
        rent_email.setText(remail);


        mProfileImage = (ImageView) findViewById(R.id.profile_bg_main);
        mProfileImage.setImageResource(R.drawable.omd2image);
        mProfileSImage = (ImageView) findViewById(R.id.profile_img_main);
        Picasso.with(this).load(R.drawable.omd2image).centerCrop().fit().into(mProfileImage);
        Picasso.with(this).load(R.drawable.omd2image).centerCrop().fit().into(mProfileSImage);
        mProfileSImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("GlobalVariables -rule (Profile_Renter_Activity)>>>>>>>>>>>>>>>>>>     "+AppSettingsData.GetRule());

                if(AppSettingsData.GetRule().contains("admin")) {
                    System.out.println("GlobalVariables -rule  (Profile_Renter_Activity)>>>>>>>>>>>>>>>>>>     admin");

                    Intent opa = new Intent(v.getContext(), Edit_Renter_Profile.class);

                    opa.putExtra("rentname", rent_name.getText());
                    opa.putExtra("rentflatnumber", rent_flatnumner.getText());
                    opa.putExtra("rentphonno", rent_phone.getText());
                    opa.putExtra("rentemailid", rent_email.getText());
                    v.getContext().startActivity(opa);
                }
                else if(AppSettingsData.GetRule().contains("owner")&AppSettingsData.getLoginFlatOwner().contains(rent_flatnumner.getText())&AppSettingsData.getFlatstatus().contains("yes"))
                {
                    Intent opa = new Intent(v.getContext(), Tenent_Update_Activity.class);
                    opa.putExtra("ownername", rent_name.getText());
                    opa.putExtra("flatnumber", rent_flatnumner.getText());
                    opa.putExtra("ownerphonno", rent_phone.getText());
                    opa.putExtra("owneremailid", rent_email.getText());
                    v.getContext().startActivity(opa);

                }
                else
                {
                    AppSettingsData.AppDialog("Update Fail","This flat you cant update kindly try flatnumber- ",AppSettingsData.getLoginFlatOwner(),Profile_Renter_Activity.this);
                }


                //startActivity(new Intent(getApplicationContext(), Edit_Owner_Profile.class));
            }
        });


        CheckBox chkIos = (CheckBox) findViewById(R.id.maintance_paid);


        System.out.println("Currrent Year>>>>>>>>>>>>>>>>>>"+year);
        System.out.println("Current Month>>>>>>>>>>>>>>>>>>>>>>"+monthName[month]);

        System.out.println("Complite String mntStatus.....................>>-------"+mntStatus);
        if(mntStatus!=null)
        if(("y"+"_"+monthName[month]).contentEquals((mntStatus)))
        chkIos.setChecked(true);


        chkIos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(Profile_Renter_Activity.this,
                            "paid Maintance :)", Toast.LENGTH_LONG).show();
                    Renter renter=new Renter();
                    renter.setFlatnumber(rent_flatnumner.getText().toString());
                    renter.setRmaintaincepaid("y"+"_"+monthName[month]);
                    UpdateMntStatus(renter);
                }

            }
        });
    }


    public void UpdateMntStatus(Renter renter)
    {
        /*
        Log.i("autolog", "getUserList");
        try {
            String url = "https://postgres2322.herokuapp.com/renter/";
            Log.i("autolog", "https://postgres2322.herokuapp.com/renter/");

            Retrofit retrofit = null;
            Log.i("autolog", "retrofit");

            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Log.i("autolog", "build();");
            }


            HerokuService service = retrofit.create(HerokuService.class);
            Log.i("autolog", " APIService service = retrofit.create(APIService.class);");


            Call<Renter> call = service.updatemntstatus(renter);
            Log.i("autolog", "Call<List<User>> call = service.updatemntstatus();");

            call.enqueue(new Callback<Renter>() {
                @Override
                public void onResponse(Call <Renter> call, Response<Renter> response) {
                    Renter user1 = response.body();
                    Toast.makeText(getApplicationContext(), "Renter Owner Details Updated", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Renter> call, Throwable t) {

                }
            });
        }catch (Exception e) {Log.i("autolog", "Exception");}*/
        System.out.println("I am here ...<Monthly expence Class>..GetLoginRule-RETROFIT");
        HerokuService apiService = RetrofitClient.getApiService();
        apiService.updatemntstatus(renter).enqueue(new Callback<Renter>() {
            @Override
            public void onResponse(Call<Renter> call, Response<Renter> response) {
                System.out.println("I am here ...<LoginActivity>..GetLoginRule-onResponse");
                if(response.isSuccessful()) {
                    Renter user1 = response.body();
                    Toast.makeText(getApplicationContext(), "Renter Owner Details Updated", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Renter>  call, Throwable t) {
                System.out.println("This in  Failure    GetLoginRule>>>>>. in login activity "+t.getMessage());
                // response_status=true;
            }
        });
    }
}