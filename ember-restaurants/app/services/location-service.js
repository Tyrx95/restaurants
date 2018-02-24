import Ember from 'ember';

export default Ember.Service.extend({
   ajaxService: Ember.inject.service(),

   allLocations: function(){
     return this.get('ajaxService').request('/getRestaurantsLocations');
   },
   addLocation: function(nameValue){
     return this.get('ajaxService').request('/admin/addLocation',{
       method: 'POST',
       data: JSON.stringify({
         name: nameValue
       })
     });
   },
   deleteLocation: function(_id){
     return this.get('ajaxService').request('/admin/deleteLocation',{
       method: 'POST',
       data: JSON.stringify({
         id: _id
       })
     });
   },
   editLocation: function(location){
     return this.get('ajaxService').request('/admin/editLocation',{
       method: 'POST',
       data: JSON.stringify({
         id: location.id,
         name:location.name
       })
     });
   },
   getLocationDetails: function(_id){
     return this.get('ajaxService').request('/admin/getLocationDetails',{
       method: 'POST',
       data: JSON.stringify({
         id: _id
       })
     });
   },
   getLocationsByFilter: function(filter){
     return this.get('ajaxService').request('/admin/getFilteredLocations',{
       method: 'POST',
       data: JSON.stringify({
         itemsPerPage: filter.itemsPerPage,
         pageNumber: filter.pageNumber,
         searchText: filter.searchText
       })
     });
   },


});
