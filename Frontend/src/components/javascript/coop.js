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
      coops: [],
      instructor: '',
      coopId: '',
      term: '',
      coopId_filter: '',
      errorCoop: '',
      errorCoop_filter: '',
      fields: {
        coopID: {
          label: "Coop ID",
          sortable: true
        },
        term: {
          label: "Term",
          sortable: true
        },
        termInstructor: {
          label: "Instructors",
          sortable: true
        },
    },
  }
  },
  created: function () {
    AXIOS.get(`/allCoops`)
      .then(response => {
        // JSON responses are automatically parsed.
        this.coops = response.data;
      })
  },

  updated() {
    if ((localStorage.getItem('loggedIn') != null)) {
      //if cookies expired, refresh
      if (this.$cookie.get("username") == null || this.$cookie.get("password") == null) {
        localStorage.removeItem('loggedIn')
        this.$cookie.delete('username');
        this.$cookie.delete('password');
        window.location.href = "/";
      }
      else {
        //reverify login information
        AXIOS.post(`/login/` + this.$cookie.get("username") + '/' + this.$cookie.get("password"), {}, {})
          .then(response => {
            if (response.data == 'TermInstructor') {
              if (localStorage.getItem('loggedIn') != "TermInstructor") {
                localStorage.setItem('loggedIn', "TermInstructor");
                console.log("Not term instructor");
                window.location.href = "/";
              }

            }
            else if (response.data == "ProgramManager") {
              if (localStorage.getItem('loggedIn') != "ProgramManager") {
                localStorage.setItem('loggedIn', "ProgramManager");
                console.log("Not program manager");
                window.location.href = "/";
              }
            }
            else {
              localStorage.removeItem('loggedIn')
              this.$cookie.delete('username');
              this.$cookie.delete('password');
              console.log("bad log in information");
              window.location.href = "/";
            }
          })
          .catch(e => {
            localStorage.removeItem('loggedIn')
            this.$cookie.delete('username');
            this.$cookie.delete('password');
            console.log("error in post request: " + e);
            window.location.href = "/";
          });
        console.log(localStorage.getItem('loggedIn'))
      }
    }
  },
  methods: {
    assignCoop: function (instructor, coopId) {
      console.log(`/assignCoop` + "?email=" + instructor + "&coopId=" + coopId)
      if(instructor == null || instructor == ''){
        this.errorCoop = 'Please enter instructor ID';
        return;
      }
      if(coopId == null || coopId == ''){
        this.errorCoop = 'Please enter coop ID';
        return;
      }
      AXIOS.post(`/assignCoop` + "?email=" + instructor + "&coopId=" + coopId, {}, {})
        .then(response => {
          // JSON responses are automatically parsed.
          this.instructor = ''
          this.coopId = ''
          this.errorCoop = ''
          AXIOS.get(`/allCoops`)
            .then(response => {
              this.coops = response.data;
            })
        })
        .catch(e => {
          var errorMsg = "Coop ID or Instructor ID not found"
          console.log(errorMsg)
          this.errorCoop = errorMsg
        });
    },
    FilterCoop: function (coopId) {
      if(coopId == null || coopId == ''){
        this.errorCoop_filter = 'Please enter coop ID';
        return;
      }
      AXIOS.post(`/Coop/` + coopId, {}, {})
        .then(response => {
          this.instructor = ''
          this.coopId_filter = ''
          this.errorCoop_filter = ''
          this.coops = response.data;
        })

        .catch(e => {
          var errorMsg = "No coops found for this ID"
          console.log(errorMsg)
          this.errorCoop_filter = errorMsg
        });
    },
    ActiveCoop: function (term) {
      AXIOS.post(`/allCoops/` + term, {}, {})
        .then(response => {
          // JSON responses are automatically parsed.
          this.instructor = ''
          this.coopId_filter = ''
          this.errorCoop_filter = ''
          this.coops = response.data;
        })
        .catch(e => {
          var errorMsg = "No Coop Found"
          console.log(errorMsg)
          this.errorCoop_filter = errorMsg
        });
    },
  }
}
