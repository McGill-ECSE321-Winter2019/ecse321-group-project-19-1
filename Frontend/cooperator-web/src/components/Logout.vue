<template>
<div id="logout">

    <div class="site-section site-hero inner" data-aos="fade-up">
        <div class="container" align="center">
            <input
                        class="login-text"
                        type="text"
                        placeholder="Email"
                        v-model="username"
                        />
            <input
                        class="login-text"
                        type="password"
                        placeholder="Password"
                        v-model="password"
                        />
            <input @click="logout()"
                        type="submit"
                        value="Logout"
                        class="btn btn-primary py-2 px-4 text-white"
                        />
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
