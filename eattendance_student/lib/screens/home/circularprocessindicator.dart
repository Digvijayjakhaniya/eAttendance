import 'package:flutter/material.dart';

class processindicator extends StatelessWidget {
  const processindicator({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        child: Center(
          child:Column(
            children: [
               CircularProgressIndicator(),
               Text('Please wait...')
            ],
          ),
        ),
      ),
    );
  }
}