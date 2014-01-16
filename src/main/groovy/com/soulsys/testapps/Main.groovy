package  com.soulsys.testapps

import com.soulsys.g_serv.GServ
/**
 * Created by lcollins on 1/14/14.
 */
class Main {

    static void main(String[] args){
        GServ.http {

            get ("/", file("text/html","index.html" ))

        }.start(8081)
    }

}
