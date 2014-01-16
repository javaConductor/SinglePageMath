/**
 * Created by lcollins on 1/17/14.
 */


import groovyx.net.http.HTTPBuilder

import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.Method.GET

class Stress {

    static void main(String[] args){

        new Stress(1000).go()


    }
    def results = []
    def _nTimes
    def Stress(nTimes){
        _nTimes = nTimes

    }
def go(){

    def then  = new Date()
    for(int i=0; i != _nTimes;++i){
        doGet( randOp(), randNum(), randNum())
    }
    def now = new Date()
    def tm = 0.0 + (now.time - then.time)
    println(" Got ${results.size()} responses in $tm ms. avg=${tm/results.size().doubleValue()} ms ")

}

    def randNum(){
        Random rand = new Random()
        int max = 2000000
       def n = rand.nextInt(max)
        return n

    }
    def randOp(){
        Random rand = new Random()
        int max = 10
        def ops = ["subtract","add", "multiply"]
        def idx = rand.nextInt(ops.size())
        return ops[idx]
    }

    def doGet(op, num1, num2){

        def http = new HTTPBuilder( 'http://localhost:8081' )

        http.request( GET, JSON ) {
            uri.path = "/$op/$num1/$num2"

            headers.'User-Agent' = 'Mozilla/5.0 Ubuntu/8.10 Firefox/3.0.4'

            // response handler for a success response code:
            response.success = { resp, json ->
                //print '.'

                // parse the JSON response object:
                //println "${json.message}"
                results << json.message
            }

            // handler for any failure status code:
            response.failure = { resp ->
                System.err.println "Unexpected error: ${resp.statusLine.statusCode} : ${resp.statusLine.reasonPhrase}"
            }
        }
    }
}
