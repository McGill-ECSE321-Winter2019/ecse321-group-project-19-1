<template>
<div id="id">
    <div class="site-wrap">
        <div class="site-section" style="padding-top:10%">
            <div class="container" style="align:center">
                <h1 class="page-title">Assign Coop Position</h1>
                <input
                        class="text"
                        type="text"
                        placeholder="CoopID"
                        v-model="coopId"
                        />
                <input
                        class="text"
                        type="text"
                        placeholder="Instructor Email"
                        v-model="instructor"
                        />
                <input @click="assignCoop(instructor, coopId)"
                        type="submit"
                        value="Assign"
                        class="btn btn-primary py-2 px-4 text-white"
                        />
                <div class="container" align="center">
                    <span v-if="errorCoop" style="color:red">Error: {{errorCoop}} </span>
                    <br>
            </div>
                    <div id="table">
                        <table class="info-table" style="width: 100%; height: 100%;">
                        <tr>
                            <th style="padding:10px">Coop ID</th>
                            <th style="padding:10px">Instructors</th>
                        </tr>
                        <tr v-for="coop in coops">
                            <td style="padding:10px">{{ coop.coopID }}</td>
                            <td style="padding:10px" v-for="ti in coop.termInstructor">
                                            {{ti.email}}</td>
                        </tr>
                    </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script src="./javascript/coop.js">
</script>
