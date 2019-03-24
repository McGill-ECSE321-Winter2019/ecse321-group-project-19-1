import axios from "axios";
import { log } from "util";
var config = require("../../../config");

var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
var backendUrl = "https://cooperator-backend-260.herokuapp.com/";

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl }
});

export default {
  data() {
    return {
      student: {},
      documents:[],
      id: null,
      coopId: null,
      username: this.$cookie.get("username") || '',
      password: this.$cookie.get("password") || '',

      fields: {
        name: {
          label: "DocumentName",
          sortable: true
        },
        dueDate: {
          label: "DueDate",
          sortable: true
        },
        documentId: {
            label: "Document Id",
            sortable: true
          },
        submitted: {
          label: "Submitted Status",
          sortable: true
        },
      }

    };
  },
  created: function() {
    this.id = this.$route.params.id
    
    AXIOS.get(`/student/` + '?studentId=' + id)
      .then(response => {
        this.student = response.data;
        console.log(this.student);
      })
      .then(() =>{
       this.coopId=this.student.coopPositions[0].coopID;
       AXIOS.get('/allRequiredDocumentsByCoopPosition' + '?coopId=' + this.coopId)
        .then(response => {
            this.documents = response.data;
            if(this.documents.length != 0){
            for(var i=0; i<this.documents.length; i++)
                this.documents[i].dueDate = this.documents[i].dueDate.substring(0,10);
            }
            this.$refs.table.refresh();
       })
      })
      .catch(error => {
        alert(error);
      })
  },

  methods:{
      adj(){
        AXIOS.post('/setCoopStatus/' + '?status=' + 'ACCEPTED'+ '&coopId='+ this.coopId +'&programManagerEmail='+ this.username + '&programManagerPassword=' + this.password)
        .then(() =>{
          AXIOS.get(`/student/` + '?studentId=' + id)
            .then(response => {
              this.student = response.data;
            })
        })
      }
  }
};
