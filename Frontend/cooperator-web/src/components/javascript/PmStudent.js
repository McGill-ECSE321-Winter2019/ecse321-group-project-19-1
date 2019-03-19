import axios from 'axios'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    data() {
        return {
          fields: {
            first_name: {
              label: 'First Name',
              sortable: false
            },
            last_name: {
              label: 'Last Name',
              sortable: true
            },
            ID:{
              label: "ID",
              sortable: true
            }
          },

          students: [
            {first_name: 'Max', last_name: 'Brodeur', ID: 260806240},
            {first_name: 'Mia', last_name: 'Zhou', ID: 260806244},
            {first_name: 'Andre', last_name: 'Kaba', ID: 260806437},
            {first_name: 'Sophie', last_name: 'Deng', ID: 260806649},
            {first_name: 'Tom', last_name: 'Temp', ID: 260806240},
            {first_name: 'Emp', last_name: 'AAA', ID: 260806244},
            {first_name: 'rick', last_name: 'Tandom', ID: 260806437},
            {first_name: 'srah', last_name: 'Random', ID: 260806649},
            {first_name: 'rick', last_name: 'People', ID: 260806240},
            {first_name: 'lol', last_name: 'There', ID: 260806244},
            {first_name: 'here', last_name: 'Aaron', ID: 260806437},
            {first_name: 'there', last_name: 'Dude', ID: 260806649},
          ]
        }
      },

    //   created: function() {
    //     // Initializing people from backend
    //     AXIOS.get(`/allStudents`)
    //       .then(response => {
    //         // JSON responses are automatically parsed.
    //         this.students = response.data;
    //       })  
    // }
}