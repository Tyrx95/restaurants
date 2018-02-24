import Ember from 'ember';

export default Ember.Controller.extend({

  locationService: Ember.inject.service(),

  actions: {

    editLocation: function(){
      this.get('locationService').editLocation(this.get('model.location')).then(function(data){
        alert('Location successfully edited');
        this.transitionToRoute('/admin/locations/')
      }.bind(this),function(data){
        alert('Error editing location');
      });
    },
  }
});
