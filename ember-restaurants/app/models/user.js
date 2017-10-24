import DS from 'ember-data';

export default DS.Model.extend({
  id: DS.attr(),
  email: DS.attr(),
  phone: DS.attr(),
  country: DS.attr(),
  city: DS.attr(),
  firstName: DS.attr(),
  lastName: DS.attr()
});
