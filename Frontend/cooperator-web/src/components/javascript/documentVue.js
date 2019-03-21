import axios from 'axios'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'https://cooperator-backend-260.herokuapp.com/'
'https://cooperator-backend-260.herokuapp.com/'

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {    
    data() {
        return {
            student: null,
            docs: [],
            username: this.$cookie.get("username") || '',

            fields: {
                studentId: {
                  label: 'StudentID',
                  sortable: true
                },
                firstName : {
                  label: 'First Name',
                  sortable: true
                },
                lastName: {
                  label: 'Last Name',
                  sortable: true
                }
              }
        }
    },
    created: function () {
        AXIOS.get(`/allStudentsByTermInstructor` + '?email' + this.username)
        .then(response => {
          this.students = response.data
        })        
    },
    methods: {
        
    }
}