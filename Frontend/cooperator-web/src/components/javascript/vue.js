import axios from 'axios'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'https://cooperator-backend-260.herokuapp.com/'

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    name: 'app',
    data() {
        return {
            username: this.$cookie.get("username") || '',
            password: this.$cookie.get("password") || '',
            errorLogin: '',
            response: '',
        }
    },
    computed: {
        loggedIn() {
            if ((localStorage.getItem('loggedIn') != null)) {
                if (this.$cookie.get("username") == null || this.$cookie.get("password") == null){
                    localStorage.removeItem('loggedIn')
                    this.$cookie.delete('username');
                    this.$cookie.delete('password');
                    window.location.href = "/";
                }
                else{
                    console.log(localStorage.getItem('loggedIn'))
                    return (localStorage.getItem('loggedIn') == "TermInstructor" || localStorage.getItem('loggedIn') == "ProgramManager")
                    console.log(localStorage.getItem('loggedIn') + "yes")
                }
            }
            return false
        },
        isPM() {
            console.log(localStorage.getItem('loggedIn'))
            return (localStorage.getItem('loggedIn') == "ProgramManager")
        },
        isTI() {
            console.log(localStorage.getItem('loggedIn'))
            return (localStorage.getItem('loggedIn') == "TermInstructor")
        }
    },
}