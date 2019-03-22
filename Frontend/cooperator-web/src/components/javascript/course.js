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
            courses: [],
            newCourse: '',
            courseName: '',
            coopId: '',
            errorCourse: '',
            errorNewCourse: '',
        }
    },
    created: function () {
        AXIOS.get(`/ranking`)
            .then(response => {
                this.courses = response.data;
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
        coopLength: function (course) {
            return course.coopPositions.length
        },

        createCourse: function (newCourse) {
            if(newCourse == null || newCourse == ''){
                this.errorNewCourse = "Please enter a Course Name"
                return
            }
            AXIOS.post(`/createCourse` + "?courseName=" + newCourse, {}, {})
                .then(response => {
                    // JSON responses are automatically parsed.
                    this.newCourse = ''
                    this.coopId = ''
                    this.errorNewCourse = ''
                    AXIOS.get(`/ranking`)
                        .then(response => {
                            this.courses = response.data;
                        })
                })
                .catch(e => {
                    var errorMsg = e.message
                    console.log(errorMsg)
                    this.errorNewCourse = errorMsg
                })
        },

        rateCourse: function (coopId, courseId) {
            if(coopId == null || coopId == ''){
                this.errorCourse = "Please enter a Coop Id"
                return
            }
            if(courseId == null || courseId == ''){
                this.errorCourse = "Please enter a Course Id"
                return
            }
            AXIOS.post(`/rateCourse` + "?courseId=" + courseId + "&coopId=" + coopId, {}, {})
                .then(response => {
                    this.courseId = ''
                    this.coopId = ''
                    this.errorCourse = ''
                    AXIOS.get(`/ranking`)
                        .then(response => {
                            this.courses = response.data;
                        })
                })
                .catch(e => {
                    var errorMsg = e.message
                    console.log(errorMsg)
                    this.errorCourse = errorMsg
                });
        },
    }
}