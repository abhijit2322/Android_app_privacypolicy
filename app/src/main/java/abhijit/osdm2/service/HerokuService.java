package abhijit.osdm2.service;

import abhijit.osdm2.models.Admin;
import abhijit.osdm2.models.FileDB;
import abhijit.osdm2.models.ImageDB;
import abhijit.osdm2.models.MonthlyExpence;
import abhijit.osdm2.models.Renter;
import abhijit.osdm2.models.FlatOwner;
import abhijit.osdm2.models.SuppTable;
import abhijit.osdm2.models.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.ArrayList;
import java.util.List;

public interface HerokuService {
    @GET("get")
    Call<ResponseBody> hello();

    @GET("getuser")
    Call<User> getUser();

    @GET("getflatowners")
    Call<List<FlatOwner>> getFlatowner();

    @GET("getsupportinfo")
    Call<List<SuppTable>> getSupportList();
   // Call<List<SupportTable>> getSupportList();

    @GET("getrenter")
    Call<List<Renter>> getRenterList();

    @GET("emailid")
    Call<List<String>> getAllEmailids();


    //=======================================================


    @POST("updatepass")
    Call<Admin> updateAdminTable(@Body Admin admin);

    @POST("updatefowner")
    Call<FlatOwner> updateflatowner(@Body FlatOwner flatOwner);

    @POST("flatownerstatus")
    Call <String> getFlatStatus(@Body FlatOwner flatOwner);


    @POST("getadmin")
    Call <List<Admin>> getAdmin(@Body Admin admin);

    @POST("getadmins")
    Call <List<Admin>> getAdmins();

    @POST("getloginrule")
    Call <String> getLoginrule(@Body Admin admin);

    @POST("updaterenter")
    Call<Renter> updaterentowner(@Body Renter renter);

    @POST("addsupport")
    Call<SuppTable> updatersupport(@Body SuppTable renter);

    @POST("mnt_status")
    Call<Renter> updatemntstatus(@Body Renter renter);

    @POST("updatemexp")
    Call<MonthlyExpence> updatemonthlyexp(@Body MonthlyExpence monthlyExpence);

    @POST("uploadfile")
    Call<FileDB> uploadfile(@Body FileDB fileDB);

   // @POST("downloadfile")
   // Call<FileDB> downloadFiles( @Body FileDB fileDB);
    @POST("downloadfile")
    Call <List<FileDB>> downloadFiles_New(@Body FileDB fileDB);

    @GET("downloadfile")
    Call<FileDB> downloadFiles(@Query("month") String month);
    @POST("getfiledb")
    Call <List<FileDB>> getfiledb(@Body FileDB admin);
    @POST("getfiledb")
    Call <List<Void>> getfiledb_void(@Body FileDB admin);

    @POST("getfiledb_array")
    Call <ArrayList<FileDB>> getfiledb_array(@Body FileDB admin);

    @POST("uploadfile")
    Call<ImageDB> uploadImage(@Body ImageDB imagedb);
    @POST("downloadimage")
    //Call<List<ImageDB>> downloadImage(@Body ImageDB imagedb);
    Call<ImageDB> downloadImage(@Body ImageDB imagedb);


}
