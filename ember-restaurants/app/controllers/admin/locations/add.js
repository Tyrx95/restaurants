import Ember from 'ember';

export default Ember.Controller.extend({

  locationService: Ember.inject.service(),
  nameValue:"",

  actions: {
    addLocation: function(){
      this.get('locationService').addLocation(this.get('nameValue')).then(function(data){
        alert('Location successfully added');
        this.transitionToRoute('/admin/locations');
      }.bind(this),function(data){
        alert('Error adding location');
      });
    }
  }
});
