import axios from 'axios'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'https://cooperator-backend-260.herokuapp.com/'

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {    
    data() {
        return {
            student: {},
            docs: [],
            gradedDoc : [],
            username: this.$cookie.get("username") || '',

            fields: {
                coopId: {
                  label: 'Coop Id',
                  sortable: true
                },
                documentId: {
                  label: 'Document Id',
                  sortable: true
                },
                dueDate : {
                  label: 'Document Due Date',
                  sortable: true
                },
                name:{
                  label: 'Document Type',
                  sortable: true
                },
                submitted:{
                  label: 'Submitted',
                  sortable: true
                },
                accepted:{
                  label: 'Graded',
                  sortable: true
                }
                
              }
        }
    },
    created: function () {
      var id = this.$route.params.id
      this.username = this.$cookie.get("username") || '';
        AXIOS.get(`/student/` + '?studentId=' + id ).
        then((response)=>{
          this.student = response.data
        }).then(()=>{
          AXIOS.get(`/allRequiredDocumentsByCoopPosition/` + '?coopId=' + this.student.coopPositions[0].coopID ).
          then((response)=>{
            this.docs = response.data
          }).then(()=>{
            for(var i=0 ; i<this.docs.length; i++){
              var doc = this.docs[i]

              if(doc.accepted)
                doc.accepted = "Accepted"
              else
                doc.accepted = "Unaccepted"

              if(doc.submitted)
                doc.submitted = "Submitted"
              else
                doc.submitted = "Not Submitted"
              
              doc.dueDate = doc.dueDate.substring(0,10)
            }
            this.$refs.table.refresh();
            
          })
        })
        
        
      
    },
    methods: {
      studentSelection(item){
        this.gradedDoc = item
    },
      grade(){
        if(this.gradedDoc.length == 0 )
          alert("Please select documents before grading (Click on a document to select it)")
          var promises = []
        for(var i=0 ; i<this.gradedDoc.length; i++){
           var doc = this.gradedDoc[i]
           promises.push(AXIOS.post(`/gradeDocument/` + '?documentId=' + doc.documentId + '&grade=true' + "&instructorEmail=" + this.username))
        }
        
        Promise.all(promises).then((res)=>{
          AXIOS.get(`/allRequiredDocumentsByCoopPosition/` + '?coopId=' + this.student.coopPositions[0].coopID ).
          then((response)=>{
            this.docs = response.data
          }).then(()=>{
            for(var i=0 ; i<this.docs.length; i++){
              var doc = this.docs[i]

              if(doc.accepted)
                doc.accepted = "Accepted"
              else
                doc.accepted = "Unaccepted"

              if(doc.submitted)
                doc.submitted = "Submitted"
              else
                doc.submitted = "Not Submitted"
              
              doc.dueDate = doc.dueDate.substring(0,10)
            }
            this.$refs.table.refresh();
            console.log(this.docs);
            console.log(res.data);
            
            
          })
        })
      }
  }
}