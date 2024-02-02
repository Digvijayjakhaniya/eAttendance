import 'package:flutter/material.dart';

class AttendanceList extends StatelessWidget {
  const AttendanceList({super.key});

  @override
  Widget build(BuildContext context) {
    return listView();
  }

  Widget listView() {
    // Calculate total percentage
    double totalPercentage = calculateTotalPercentage();

    return Column(
      children: <Widget>[
        Expanded(
          child: ListView.separated(
            itemBuilder: (context, index) {
              return listViewItem(index);
            },
            separatorBuilder: (context, index) {
              return const Divider(height: 5);
            },
            itemCount: 5,
          ),
        ),
        // Total percentage widget
        Container(
          alignment: Alignment.center,
          padding: const EdgeInsets.all(10),
          color: Colors.green,
          child: Text(
            'Total Percentage: ${totalPercentage.toStringAsFixed(2)}%',
            style: const TextStyle(
              fontWeight: FontWeight.bold,
              fontSize: 20.0,
            ),
          ),
        ),
      ],
    );
  }

  Widget listViewItem(int index) {
    return Container(
      margin: const EdgeInsets.symmetric(horizontal: 10, vertical: 10),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Expanded(
            child: Container(
              margin: const EdgeInsets.only(left: 10),
              child: const Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Padding(
                    padding: EdgeInsets.only(left: 8.0, top: 5.0),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          'Subject Name',
                          style: TextStyle(
                            fontWeight: FontWeight.bold,
                            fontSize: 20.0,
                          ),
                        ),
                      ],
                    ),
                  )
                ],
              ),
            ),
          ),
          const Padding(
            padding: EdgeInsets.only(right: 8.0, top: 5.0),
            child: Text('70%',
                style: TextStyle(fontWeight: FontWeight.bold, fontSize: 20.0)),
          )
        ],
      ),
    );
  }

  double calculateTotalPercentage() {
    // Perform the calculation here based on your data
    // For now, returning a fixed value
    return 75.0;
  }
}
