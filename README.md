# My_first_android_app

General code/layouts:
- format code using proper style: https://docs.google.com/document/d/1GVEXbSavsfrl9wJ0ZTQ1MQUZ3rwMddOvO__EaEoXsDY/edit#
- name classes and resources following the given conventions: https://docs.google.com/document/d/15BFumZk2emGu1GlK_D5eVfhrSgPoij-Xic905D3c5KM/edit
- avoid usage of !!
- packages' names should only have lower-case letters
- data module:
-- package "local_data_base" should be renamed to "database"
-- package "utils" should be renamed to "mappers"
-- in "service" package, an "api" package should be created with APIService class
-- "responseObjects" package should be renamed to "response" and moved inside "service"
- app module:
-- follow files resources naming convention
-- rename "viewModel" package to "viewmodels"
-- view models should all be in the same package "viewmodels"
-- fragment_lot_menu.xml can be deleted, it's never used
-- strings exclusive for previews don't belong to strings.xml, they can be hardcoded into the layout
using attribute "tools:text" ("day, "month_and_year", "hour_of_day")
-- all layouts:
--- textSize attributes should be set to sp values
--- ImageButton doesn't need clickable=true attribute
--- ImageView need to have a contentDescription assigned
--- FloatingActionButton doesn't need clickable=true attribute
-- layout_delete_dialog.xml: LinearLayout can be removed

Bugs:
- Parking List: An active reservation is not shown parking as busy
- Parking List: Parking is shown as busy with a reservation star date, instead of finish date
- Parking List: Parking list is shown as empty on connectivity problem
- Parking Details: Reservation list is not refreshed after adding a reservation
- Add Reservation: Click on Authorization code is needed to skip empty field validation
- Add Reservation: From Parking Details doesn't prefill Parking Lot field
- Add Reservation: Date Picker cancel button opens Time Picker

Tests:
- Use case tests belong to "domain" module