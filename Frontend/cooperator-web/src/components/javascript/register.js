import axios from 'axios'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    name: 'register',
    data() {
        return {
            firstName: '',
            lastName: '',
            email: '',
            password: '',
            selected: 'Select',
            errorRegister: '',
            response: '',
        }
    },
    created: function () {
    
    },
    methods: {
        register(firstName, lastName, email, password, selected) {
            console.log(selected)
            if (firstName == '') {
                var errorMsg = "Unvalid first name"
                console.log(errorMsg)
                this.errorRegister = errorMsg
                return
            }
            if (lastName == '') {
                var errorMsg = "Unvalid last name"
                console.log(errorMsg)
                this.errorRegister = errorMsg
                return
            }
            if (email == '') {
                var errorMsg = "Unvalid email"
                console.log(errorMsg)
                this.errorRegister = errorMsg
                return
            }
            if (password == '') {
                var errorMsg = "Unvalid password"
                console.log(errorMsg)
                this.errorRegister = errorMsg
                return
            }
            this.errorRegister = ''
            if(selected == "TermInstructor"){
                AXIOS.post(`/createTermInstructor/` + email + "?lastName=" + lastName + "&firstName=" + firstName + "&password=" + password, {}, {})
                .then(response => {
                    // JSON responses are automatically parsed.
                    this.response = response.data
                    console.log(this.response)
                    this.response = "Term Instructor Created!"
                    this.firstName= ''
                    this.lastName= ''
                    this.email= ''
                    this.password= ''
                    this.selected= 'Select'
                })
                .catch(e => {
                    var errorMsg = e.message
                    console.log(errorMsg)
                    this.errorRegister = errorMsg
                    this.response = ''
                });
            }
            else if(selected == "ProgramManager"){
                AXIOS.post(`/createProgramManager/` + email + "?lastName=" + lastName + "&firstName=" + firstName + "&password=" + password, {}, {})
                .then(response => {
                    // JSON responses are automatically parsed.
                    this.response = response.data
                    console.log(this.response)
                    this.response = "Program Manager Created!"
                    this.errorRegister = ''
                    this.firstName= ''
                    this.lastName= ''
                    this.email= ''
                    this.password= ''
                    this.selected= 'Select'
                })
                .catch(e => {
                    var errorMsg = e.message
                    console.log(errorMsg)
                    this.errorRegister = errorMsg
                    this.response = ''
                });
            }
            else{
                var errorMsg = "Please select what you want to create"
                console.log(errorMsg)
                this.errorRegister = errorMsg
                this.response = ''
            }
        }
    }
}