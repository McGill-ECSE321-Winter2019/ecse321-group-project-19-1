import axios from 'axios'
var config = require('../../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
    name: 'login',
    data() {
        return {
            username: this.$cookie.get("username") || '',
            password: this.$cookie.get("password") || '',
            errorLogin: '',
            response: '',
        }
    },
    methods: {
        login(username, password) {
            if (username == '') {
                var errorMsg = "Unvalid username"
                console.log(errorMsg)
                this.errorLogin = errorMsg
                return
            }
            if (password == '') {
                var errorMsg = "Unvalid password"
                console.log(errorMsg)
                this.errorLogin = errorMsg
                return
            }
            AXIOS.post(`/login/` + username + '/' + password, {}, {})
                .then(response => {
                    // JSON responses are automatically parsed.
                    this.response = response.data
                    this.errorLogin = ''
                    //store username and password in local storage
                    //localStorage.setItem('username', username)
                    //localStorage.setItem('password', password)
                    this.$cookie.set("username", username, { expires: '1h' })
                    this.$cookie.set("password", password, { expires: '1h' })
                    this.username = this.$cookie.get("username") || ''
                    this.password = this.$cookie.get("password") || ''
                    if (this.response == 'TermInstructor') {
                        localStorage.setItem('loggedIn', true)
                        vm.$forceUpdate();
                        this.$router.push('/TiStudentView')
                        
                    }
                    if (this.response == "ProgramManager") {
                        localStorage.setItem('loggedIn', true)
                        vm.$forceUpdate();
                        this.$router.push('/ProgramManager')
                        
                    }
                    this.errorLogin = response.data
                    console.log(this.response)

                })
                .catch(e => {
                    var errorMsg = e.message
                    console.log(errorMsg)
                    this.errorLogin = errorMsg
                });
        }
    }
}