package abhijit.osdm2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Profile_Main2Activity extends AppCompatActivity {

    @BindView(R.id.profile_bg)
    ImageView mProfileImage;
    @BindView(R.id.profile_img)
    ImageView mProfileSImage;
   // @BindView(R.id.app_bar)
    //Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity_main2);
        ButterKnife.bind(this);
      //  setSupportActionBar(toolbar);
     /*   getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
   */     Picasso.with(this).load(R.drawable.omd2image).centerCrop().fit().into(mProfileImage);
        Picasso.with(this).load(R.drawable.omd2image).centerCrop().fit().into(mProfileSImage);
        mProfileSImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
}
