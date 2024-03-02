import com.cqrit.spycket.AnalysisView;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface analysisList {
    @GET("hello")
    Call<List<AnalysisView>> getTodos();
}
