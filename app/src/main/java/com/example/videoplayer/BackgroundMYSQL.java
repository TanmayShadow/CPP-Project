import android.content.Context;
import android.os.AsyncTask;

public class BackgroundMYSQL extends AsyncTask<Void,Void,Void> {

    Context context;
    BackgroundMYSQL(Context c1)
    {
        this.context=c1;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
