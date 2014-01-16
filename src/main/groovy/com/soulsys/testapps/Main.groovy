package  com.soulsys.testapps

import com.soulsys.g_serv.GServ
/**
 * Created by lcollins on 1/14/14.
 */
class Main {

    static void main(String[] args){
        GServ.http {
            useResourceDocs(true)
            get ("/", file("text/html","views/index.html" ))
            get ("/add/:num1/:num2"){ num1, num2 ->
                def n1 = Integer.parseInt(num1)
                def n2 = Integer.parseInt(num2)
                writeJson(  [ num1:n1 , num2: n2, answer: n2+n1, message:"$n1 + $n2 = ${n1+n2}"]  )
            }
            get ("/subtract/:num1/:num2"){ num1, num2 ->
                def n1 = Integer.parseInt(num1)
                def n2 = Integer.parseInt(num2)
                writeJson(  [ num1:n1 , num2: n2, answer: n1-n2, message:"$n1 - $n2 = ${n1-n2}"]  )
            }
            get ("/multiply/:num1/:num2"){ num1, num2 ->
                def n1 = Integer.parseInt(num1)
                def n2 = Integer.parseInt(num2)
                writeJson(  [ num1:n1 , num2: n2, answer: n2*n1, message:"$n1 * $n2 = ${n1*n2}"]  )
            }
            get ("/divide/:num1/:num2"){ num1, num2 ->
                def n1 = Integer.parseInt(num1)
                def n2 = Integer.parseInt(num2)
                if(n2 == 0)
                {
                    error(400, "Cannot divide by 0!");
                }
                writeJson(  [ num1:n1 , num2: n2, answer: n1/n2, message:"$n1 / $n2 = ${n1/n2}"]  )
            }
            get ("/exponent/:num1/:num2"){ num1, num2 ->
                def n1 = Integer.parseInt(num1)
                def n2 = Integer.parseInt(num2)
                if(n2 == 0)
                {
                    error(400, "Cannot divide by 0!");
                }
                writeJson(  [ num1:n1 , num2: n2, answer: n1/n2, message:"$n1 to the power $n2 = ${Math.pow( n1,n2)}"]  )
            }
            get ("/modulo/:num1/:num2"){ num1, num2 ->
                def n1 = Integer.parseInt(num1)
                def n2 = Integer.parseInt(num2)
                if(n2 == 0)
                {
                    error(400, "Cannot divide by 0!");
                }
                writeJson(  [ num1:n1 , num2: n2, answer: n1%n2, message:"$n1 % $n2 = ${n1%n2}"]  )
            }

        }.start(8081)
    }

}
