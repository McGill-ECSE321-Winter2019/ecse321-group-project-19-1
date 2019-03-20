<template>
<div id="logout">

    <div class="site-section site-hero inner">
        <div class="half left">
            <div class="container" align="center">
                <div class="row align-items-center" align="center">
                    <div class="col-md-10" align="center" style="padding-left:10%">
                        <span class="d-block mb-3 caption">ECSE 321</span>
                        <h1 class="d-block mb-4">Coop-erator</h1>
                    </div>
                </div>
            </div>
        </div>
        <div class="half right"  data-aos="fade-up">
            <div class="container" align="center">
                <div class="login-grid" style="padding-right:10%">
                    <span class="d-block mb-3 caption">Do You want to Log out?</span>
            <input @click="logout()"
                        type="submit"
                        value="Logout"
                        class="btn btn-primary py-2 px-4 text-white"
                        />
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'logout',
    methods: {
        logout() {
            localStorage.removeItem('loggedIn')            
            this.$cookie.delete('username');
            this.$cookie.delete('password');
            window.location.href = "/";
        }
    }
}</script>
