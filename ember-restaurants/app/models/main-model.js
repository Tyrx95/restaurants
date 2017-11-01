import Ember from 'ember';

export default Ember.Object.extend({
  modelProps: [],

  getProperties: function() {
    return this.get('modelProps');
  },

  serialize: function() {
    var model = {};
    var model_props = this.getModelProperties();
    for (var prop of model_props) {
      model[prop] = this.get(prop);
    }
    return JSON.stringify(model);

  },

  deserialize: function() {}
});
