<template>
<div id="id">
    <div class="site-wrap">
        <div class="site-section" style="padding-top:10%">
            <div class="container" style="text-align:center; ">
                <h1 class="page-title">Assign Coop Position</h1>
                <input
                        class="text"
                        type="text"
                        style="margin: 15px; border-radius:2%;"
                        placeholder="CoopID"
                        v-model="coopId"
                        />
                <input
                        class="text"
                        type="text"
                        style="margin: 15px; border-radius:2%;"
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
                        <h1 class="page-title">Filter Coops</h1>
                        <input
                        class="text"
                        type="text"
                        style="margin: 15px; border-radius:2%;"
                        placeholder="CoopID"
                        v-model="coopId_filter"
                        />
                        <input @click="FilterCoop(coopId_filter)"
                        type="submit"
                        value="Find"
                        class="btn btn-primary py-2 px-4 text-white"
                        />
                        <input
                        class="text"
                        type="text"
                        style="margin: 15px; border-radius:2%;"
                        placeholder="Term"
                        v-model="term"
                        />
                        <input @click="ActiveCoop(term)"
                        type="submit"
                        value="View Coops by Term"
                        class="btn btn-primary py-2 px-4 text-white"
                        />
                    <div class="container" align="center">
                            <span v-if="errorCoop_filter" style="color:red">Error: {{errorCoop_filter}} </span>
                            <br>
                    </div>
                            <div id="table">
                                <b-table ref="table" class="info-table" striped :items="coops" :fields="fields" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
</template>

<script src="./javascript/coop.js">
</script>
