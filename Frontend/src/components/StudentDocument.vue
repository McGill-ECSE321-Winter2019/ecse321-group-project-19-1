<template>
<div id="id">
    
    <div class="site-section site-hero inner">
            <div class="container" align="center">
                <h2 align="center" class="page-title" >Documents of {{student.firstName}}, {{student.lastName}}
                    <input @click="grade()"
                        type="submit"
                        value="Grade"
                        class="btn btn-primary py-2 px-4 text-white"
                        />
                </h2>
                
                        <div id="table" style="height= 1500px">
                            <b-table ref="table" class="info-table" selectable select-mode="multi"  striped hover :items= "docs" :fields= "fields" @row-selected="studentSelection"/>
                        </div>
            </div>
        </div>
</div>
</template>

<script src="./javascript/documentVue.js">
</script>
